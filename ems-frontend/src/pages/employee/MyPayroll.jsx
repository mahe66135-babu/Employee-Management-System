import { useEffect, useState } from "react";
import EmployeeLayout from "../../components/layouts/EmployeeLayout";
import { getMyPayroll } from "../../services/payrollService";

function MyPayroll() {

    const [payrolls, setPayrolls] = useState([]);

    useEffect(() => {

        loadPayroll();

    }, []);

    const loadPayroll = async () => {

        try {

            const response = await getMyPayroll();

            setPayrolls(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <EmployeeLayout>

            <div className="card shadow">

                <div className="card-header">

                    <h3>My Payroll</h3>

                </div>

                <div className="card-body">

                    <div className="table-responsive">

                        <table className="table table-bordered">

                            <thead className="table-dark">

                            <tr>

                                <th>Month</th>
                                <th>Basic Salary</th>
                                <th>Bonus</th>
                                <th>Deduction</th>
                                <th>Net Salary</th>
                                <th>Payment Date</th>

                            </tr>

                            </thead>

                            <tbody>

                            {payrolls.length > 0 ? (

                                payrolls.map(payroll => (

                                    <tr key={payroll.id}>

                                        <td>{payroll.month}</td>
                                        <td>{payroll.basicSalary}</td>
                                        <td>{payroll.bonus}</td>
                                        <td>{payroll.deduction}</td>
                                        <td>{payroll.netSalary}</td>
                                        <td>{payroll.paymentDate}</td>

                                    </tr>

                                ))

                            ) : (

                                <tr>

                                    <td
                                        colSpan="6"
                                        className="text-center"
                                    >
                                        No Payroll Available
                                    </td>

                                </tr>

                            )}

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>

        </EmployeeLayout>

    );

}

export default MyPayroll;