import { useEffect, useState } from "react";
import { getRecentEmployees } from "../../services/employeeService";

function RecentEmployees() {

    const [employees, setEmployees] = useState([]);

    useEffect(() => {

        loadRecentEmployees();

    }, []);

    const loadRecentEmployees = async () => {
        try {
            const response = await getRecentEmployees();

            console.log("Recent Employees:", response.data);

            setEmployees(response.data);

        } catch (error) {
            console.error(error);
        }
    };

    return (

        <div className="card shadow mt-4">

            <div className="card-header">

                <h5>Recent Employees</h5>

            </div>

            <div className="card-body">

                <table className="table">

                    <thead>

                    <tr>

                        <th>Name</th>

                        <th>Department</th>

                        <th>Designation</th>

                    </tr>

                    </thead>

                    <tbody>

                    {employees.map(employee => (

                        <tr key={employee.id}>

                            <td>
                                {employee.firstName} {employee.lastName}
                            </td>

                            <td>{employee.departmentName}</td>

                            <td>{employee.designation}</td>

                        </tr>

                    ))}

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default RecentEmployees;