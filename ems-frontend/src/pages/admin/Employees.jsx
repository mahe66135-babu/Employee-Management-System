import { useEffect, useState } from "react";
import AdminLayout from "../../components/layouts/AdminLayout";
import { FaEye, FaEdit, FaTrash } from "react-icons/fa";
import EmployeeForm from "../../components/common/EmployeeForm";
import Swal from "sweetalert2";
import {
    getEmployees,
    deleteEmployee,
    exportEmployeesExcel
} from "../../services/employeeService";
import { exportEmployeesPdf } from "../../services/employeeService";

function Employees() {

    const [employees, setEmployees] = useState([]);
    const [search, setSearch] = useState("");
    const [showModal, setShowModal] = useState(false);
    const [selectedEmployee, setSelectedEmployee] = useState(null);
    const [viewEmployee, setViewEmployee] = useState(null);
    const [showViewModal, setShowViewModal] = useState(false);

    const loadEmployees = async () => {

        try {

            const response = await getEmployees();

            setEmployees(response.data.content);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadEmployees();

    }, []);
    const handleDelete = async (id) => {

        const result = await Swal.fire({

            title: "Delete Employee?",

            text: "This action cannot be undone.",

            icon: "warning",

            showCancelButton: true,

            confirmButtonColor: "#d33",

            cancelButtonColor: "#3085d6",

            confirmButtonText: "Yes, Delete"

        });

        if (result.isConfirmed) {

            try {

                await deleteEmployee(id);

                Swal.fire(
                    "Deleted!",
                    "Employee deleted successfully.",
                    "success"
                );

                loadEmployees();

            } catch (error) {

                console.error(error);

                Swal.fire(
                    "Error",
                    "Unable to delete employee.",
                    "error"
                );

            }

        }

    };
    const handleExportExcel = async () => {

        try {

            const response = await exportEmployeesExcel();

            const url = window.URL.createObjectURL(
                new Blob([response.data])
            );

            const link = document.createElement("a");

            link.href = url;

            link.setAttribute(
                "download",
                "employees.xlsx"
            );

            document.body.appendChild(link);

            link.click();

            link.remove();

        } catch (error) {

            console.error(error);

            Swal.fire(
                "Error",
                "Unable to export employees.",
                "error"
            );

        }

    };
    const handleExportPdf = async () => {

        try {

            const response = await exportEmployeesPdf();

            const url = window.URL.createObjectURL(
                new Blob([response.data])
            );

            const link = document.createElement("a");

            link.href = url;

            link.download = "employees.pdf";

            document.body.appendChild(link);

            link.click();

            link.remove();

        } catch (error) {

            console.error(error);

        }

    };

    const filteredEmployees = employees.filter(employee =>
        employee.firstName.toLowerCase().includes(search.toLowerCase()) ||
        employee.lastName.toLowerCase().includes(search.toLowerCase()) ||
        employee.email.toLowerCase().includes(search.toLowerCase())
    );


    return (


        <AdminLayout>

            <div className="card shadow">

                <div className="card-header d-flex justify-content-between align-items-center">

                    <h3 className="mb-0">

                        Employees

                    </h3>

                    <div>
                        <button
                            className="btn btn-danger me-2"
                            onClick={handleExportPdf}
                        >
                            Export PDF
                        </button>

                        <button
                            className="btn btn-success me-2"
                            onClick={handleExportExcel}
                        >
                            Export Excel
                        </button>

                        <button
                            className="btn btn-primary"
                            onClick={() => {
                                setSelectedEmployee(null);
                                setShowModal(true);
                            }}
                        >
                            + Add Employee
                        </button>

                    </div>

                </div>

                <div className="card-body">

                    <input
                        className="form-control mb-4"
                        placeholder="Search Employee..."
                        value={search}
                        onChange={(e)=>setSearch(e.target.value)}
                    />

                    <div className="table-responsive">

                        <table className="table table-hover align-middle">

                            <thead className="table-dark">

                            <tr>

                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Department</th>
                                <th>Designation</th>
                                <th width="180">Actions</th>

                            </tr>

                            </thead>

                            <tbody>

                            {filteredEmployees.length>0 ? (

                                filteredEmployees.map(employee=>(

                                    <tr key={employee.id}>

                                        <td>{employee.id}</td>

                                        <td>

                                            {employee.firstName} {employee.lastName}

                                        </td>

                                        <td>{employee.email}</td>

                                        <td>{employee.departmentName}</td>

                                        <td>{employee.designation}</td>

                                        <td>

                                            <button
                                                className="btn btn-info btn-sm me-2"
                                                onClick={() => {
                                                    setViewEmployee(employee);
                                                    setShowViewModal(true);
                                                }}
                                            >
                                                <FaEye />
                                            </button>

                                            <button
                                                className="btn btn-warning btn-sm me-2"
                                                onClick={() => {

                                                    setSelectedEmployee(employee);
                                                    setShowModal(true);

                                                }}
                                            >
                                                <FaEdit />
                                            </button>

                                            <button
                                                className="btn btn-danger btn-sm"
                                                onClick={() => handleDelete(employee.id)}
                                            >
                                                <FaTrash />
                                            </button>

                                        </td>

                                    </tr>

                                ))

                            ) : (

                                <tr>

                                    <td colSpan="6" className="text-center">

                                        No Employees Found

                                    </td>

                                </tr>

                            )}

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

                    <div className="modal-dialog modal-lg">

                        <div className="modal-content">

                            <div className="modal-header">

                                <h5>
                                    {selectedEmployee ? "Edit Employee" : "Add Employee"}
                                </h5>

                                <button
                                    className="btn-close"
                                    onClick={() => setShowModal(false)}
                                />

                            </div>

                            <div className="modal-body">

                                <EmployeeForm
                                    employeeData={selectedEmployee}
                                    onSuccess={() => {

                                        setShowModal(false);

                                        setSelectedEmployee(null);

                                        loadEmployees();

                                    }}
                                />

                            </div>

                        </div>

                    </div>

                </div>

            )}
            {showViewModal && (

                <div
                    className="modal d-block"
                    style={{ background: "rgba(0,0,0,0.5)" }}
                >

                    <div className="modal-dialog">

                        <div className="modal-content">

                            <div className="modal-header">

                                <h5>Employee Details</h5>

                                <button
                                    className="btn-close"
                                    onClick={() => setShowViewModal(false)}
                                />

                            </div>

                            <div className="modal-body">

                                <table className="table table-bordered">

                                    <tbody>

                                    <tr>
                                        <th>ID</th>
                                        <td>{viewEmployee?.id}</td>
                                    </tr>

                                    <tr>
                                        <th>Name</th>
                                        <td>
                                            {viewEmployee?.firstName} {viewEmployee?.lastName}
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>Email</th>
                                        <td>{viewEmployee?.email}</td>
                                    </tr>

                                    <tr>
                                        <th>Phone</th>
                                        <td>{viewEmployee?.phone}</td>
                                    </tr>

                                    <tr>
                                        <th>Department</th>
                                        <td>{viewEmployee?.departmentName}</td>
                                    </tr>

                                    <tr>
                                        <th>Designation</th>
                                        <td>{viewEmployee?.designation}</td>
                                    </tr>

                                    <tr>
                                        <th>Salary</th>
                                        <td>{viewEmployee?.salary}</td>
                                    </tr>

                                    <tr>
                                        <th>Joining Date</th>
                                        <td>{viewEmployee?.joiningDate}</td>
                                    </tr>

                                    </tbody>

                                </table>

                            </div>

                            <div className="modal-footer">

                                <button
                                    className="btn btn-secondary"
                                    onClick={() => setShowViewModal(false)}
                                >
                                    Close
                                </button>

                            </div>

                        </div>

                    </div>

                </div>

            )}

        </AdminLayout>

    );

}

export default Employees;