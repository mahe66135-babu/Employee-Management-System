import { useEffect, useState } from "react";
import { getDepartments } from "../../services/departmentService";
import { addEmployee, updateEmployee } from "../../services/employeeService";

function EmployeeForm({ employeeData, onSuccess }) {

    const [departments, setDepartments] = useState([]);
    useEffect(() => {

        loadDepartments();

    }, []);

    const loadDepartments = async () => {

        try {

            const response = await getDepartments();

            setDepartments(response.data);

        } catch (error) {

            console.error(error);

        }

    };


    const emptyEmployee = {
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        designation: "",
        salary: "",
        joiningDate: "",
        departmentId: ""
    };

    const [employee, setEmployee] = useState(emptyEmployee);

    useEffect(() => {
        setEmployee(employeeData ?? emptyEmployee);
    }, [employeeData]);

    const handleChange = (e) => {

        setEmployee({
            ...employee,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            if (employee.id) {

                await updateEmployee(employee.id, employee);

                alert("Employee Updated Successfully");

            } else {

                await addEmployee(employee);

                alert("Employee Added Successfully");

            }

            onSuccess();

        } catch (error) {

            console.error(error);

            alert("Operation Failed");

        }

    };

    return (

        <form onSubmit={handleSubmit}>

            <div className="row">

                <div className="col-md-6 mb-3">

                    <input
                        className="form-control"
                        name="firstName"
                        placeholder="First Name"
                        value={employee.firstName}
                        onChange={handleChange}
                    />

                </div>

                <div className="col-md-6 mb-3">

                    <input
                        className="form-control"
                        name="lastName"
                        value={employee.lastName}
                        onChange={handleChange}
                    />

                </div>

                <div className="col-md-6 mb-3">

                    <input
                        className="form-control"
                        placeholder="Email"
                        name="email"
                        onChange={handleChange}
                    />

                </div>

                <div className="col-md-6 mb-3">

                    <input
                        className="form-control"
                        placeholder="Phone"
                        name="phone"
                        onChange={handleChange}
                    />

                </div>

                <div className="col-md-6 mb-3">

                    <input
                        className="form-control"
                        placeholder="Designation"
                        name="designation"
                        onChange={handleChange}
                    />

                </div>

                <div className="col-md-6 mb-3">

                    <input
                        type="number"
                        className="form-control"
                        placeholder="Salary"
                        name="salary"
                        onChange={handleChange}
                    />

                </div>

                <div className="col-md-6 mb-3">

                    <input
                        type="date"
                        className="form-control"
                        name="joiningDate"
                        onChange={handleChange}
                    />

                </div>

                <div className="col-md-6 mb-3">

                    <select
                        className="form-select"
                        name="departmentId"
                        value={employee.departmentId}
                        onChange={handleChange}
                    >

                        <option value="">
                            Select Department
                        </option>

                        {departments.map((department) => (

                            <option
                                key={department.id}
                                value={department.id}
                            >

                                {department.departmentName}

                            </option>

                        ))}

                    </select>

                </div>

            </div>

            <button className="btn btn-success">

                Save Employee

            </button>

        </form>

    );

}

export default EmployeeForm;