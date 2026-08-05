import { useEffect, useState } from "react";
import AdminLayout from "../../components/layouts/AdminLayout";
import {
    getPayrolls,
    deletePayroll,
    downloadSalarySlip
} from "../../services/payrollService";
import PayrollForm from "../../components/common/PayrollForm";
import { FaTrash } from "react-icons/fa";
import Swal from "sweetalert2";

function Payroll() {

    const [payrolls, setPayrolls] = useState([]);
    const [showModal, setShowModal] = useState(false);

    const loadPayrolls = async () => {

        try {

            const response = await getPayrolls();

            setPayrolls(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadPayrolls();

    }, []);

    const handleDelete = async (id) => {

        const result = await Swal.fire({

            title: "Delete Payroll?",

            text: "This action cannot be undone.",

            icon: "warning",

            showCancelButton: true,

            confirmButtonText: "Delete"

        });

        if (result.isConfirmed) {

            await deletePayroll(id);

            Swal.fire(
                "Deleted",
                "Payroll deleted successfully",
                "success"
            );

            loadPayrolls();

        }

    };
    const handleDownload = async (id) => {

        try {

            const response = await downloadSalarySlip(id);

            const url = window.URL.createObjectURL(
                new Blob([response.data], {
                    type: "application/pdf"
                })
            );

            const link = document.createElement("a");

            link.href = url;
            link.download = `salary-slip-${id}.pdf`;

            document.body.appendChild(link);

            link.click();

            link.remove();

            window.URL.revokeObjectURL(url);

        } catch (error) {

            console.error(error);

            Swal.fire(
                "Error",
                "Unable to download salary slip.",
                "error"
            );

        }

    };


    return (

        <AdminLayout>

            <div className="card shadow">

                <div className="card-header d-flex justify-content-between">

                    <h3>Payroll Management</h3>

                    <button
                        className="btn btn-primary"
                        onClick={() => setShowModal(true)}
                    >
                        + Generate Payroll
                    </button>


                </div>

                <div className="card-body">

                    <div className="table-responsive">

                        <table className="table table-bordered table-hover">

                            <thead className="table-dark">

                            <tr>

                                <th>ID</th>
                                <th>Employee</th>
                                <th>Month</th>
                                <th>Basic Salary</th>
                                <th>Bonus</th>
                                <th>Deduction</th>
                                <th>Net Salary</th>
                                <th>Payment Date</th>
                                <th>Action</th>

                            </tr>

                            </thead>

                            <tbody>

                            {payrolls.map(payroll => (

                                <tr key={payroll.id}>

                                    <td>{payroll.id}</td>

                                    <td>{payroll.employeeName}</td>

                                    <td>{payroll.month}</td>

                                    <td>{payroll.basicSalary}</td>

                                    <td>{payroll.bonus}</td>

                                    <td>{payroll.deduction}</td>

                                    <td>{payroll.netSalary}</td>

                                    <td>{payroll.paymentDate}</td>

                                    <td>

                                        <button
                                            className="btn btn-primary btn-sm me-2"
                                            onClick={() => handleDownload(payroll.id)}
                                        >
                                            Salary Slip
                                        </button>

                                        <button
                                            className="btn btn-danger btn-sm"
                                            onClick={() => handleDelete(payroll.id)}
                                        >
                                            <FaTrash />
                                        </button>

                                    </td>

                                </tr>

                            ))}

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>

            {showModal && (

                <div
                    className="modal d-block"
                    style={{ background: "rgba(0,0,0,0.5)" }}
                >

                    <div className="modal-dialog">

                        <div className="modal-content">

                            <div className="modal-header">

                                <h5>Generate Payroll</h5>

                                <button
                                    className="btn-close"
                                    onClick={() => setShowModal(false)}
                                />

                            </div>

                            <div className="modal-body">

                                <PayrollForm
                                    onSuccess={() => {

                                        setShowModal(false);

                                        loadPayrolls();

                                    }}
                                />

                            </div>

                        </div>

                    </div>

                </div>

            )}

        </AdminLayout>

    );

}

export default Payroll;