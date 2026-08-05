import { useEffect, useState } from "react";
import { generatePayroll } from "../../services/payrollService";
import { getEmployees } from "../../services/employeeService";
import Swal from "sweetalert2";

function PayrollForm({ onSuccess }) {

    const [employees, setEmployees] = useState([]);

    const [payroll, setPayroll] = useState({
        employeeId: "",
        month: "",
        bonus: "",
        deduction: ""
    });

    useEffect(() => {

        loadEmployees();

    }, []);

    const loadEmployees = async () => {

        try {

            const response = await getEmployees();

            setEmployees(response.data.content);

        } catch (error) {

            console.error(error);

        }

    };

    const handleChange = (e) => {

        setPayroll({
            ...payroll,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await generatePayroll(payroll);

            Swal.fire(
                "Success",
                "Payroll generated successfully",
                "success"
            );

            onSuccess();

        } catch (error) {

            console.error(error);

            Swal.fire(
                "Error",
                error.response?.data?.message || "Unable to generate payroll",
                "error"
            );

        }

    };

    return (

        <form onSubmit={handleSubmit}>

            <div className="mb-3">

                <label className="form-label">

                    Employee

                </label>

                <select
                    className="form-select"
                    name="employeeId"
                    value={payroll.employeeId}
                    onChange={handleChange}
                    required
                >

                    <option value="">
                        Select Employee
                    </option>

                    {employees.map(employee => (

                        <option
                            key={employee.id}
                            value={employee.id}
                        >
                            {employee.firstName} {employee.lastName}
                        </option>

                    ))}

                </select>

            </div>

            <div className="mb-3">

                <label className="form-label">

                    Month

                </label>

                <input
                    type="text"
                    className="form-control"
                    name="month"
                    placeholder="Example: July 2026"
                    value={payroll.month}
                    onChange={handleChange}
                    required
                />

            </div>

            <div className="mb-3">

                <label className="form-label">

                    Bonus

                </label>

                <input
                    type="number"
                    className="form-control"
                    name="bonus"
                    value={payroll.bonus}
                    onChange={handleChange}
                    required
                />

            </div>

            <div className="mb-3">

                <label className="form-label">

                    Deduction

                </label>

                <input
                    type="number"
                    className="form-control"
                    name="deduction"
                    value={payroll.deduction}
                    onChange={handleChange}
                    required
                />

            </div>

            <button
                className="btn btn-success w-100"
                type="submit"
            >
                Generate Payroll
            </button>

        </form>

    );

}

export default PayrollForm;