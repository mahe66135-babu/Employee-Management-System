import { useEffect, useState } from "react";
import LeaveForm from "../../components/common/LeaveForm";
import { getMyLeaves } from "../../services/leaveService";
import EmployeeLayout from "../../components/layouts/EmployeeLayout";

function MyLeaves() {

    const [leaves, setLeaves] = useState([]);

    const loadLeaves = async () => {

        try {

            const response = await getMyLeaves();

            setLeaves(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadLeaves();

    }, []);

    return (
        <EmployeeLayout>

        <div className="container mt-4">

            <div className="card shadow mb-4">

                <div className="card-header">

                    <h4>Apply Leave</h4>

                </div>

                <div className="card-body">

                    <LeaveForm onSuccess={loadLeaves} />

                </div>

            </div>

            <div className="card shadow">

                <div className="card-header">

                    <h4>My Leave History</h4>

                </div>

                <div className="card-body">

                    <table className="table table-bordered">

                        <thead className="table-dark">

                        <tr>

                            <th>Start</th>
                            <th>End</th>
                            <th>Reason</th>
                            <th>Status</th>

                        </tr>

                        </thead>

                        <tbody>

                        {leaves.map((leave) => (

                            <tr key={leave.id}>

                                <td>{leave.startDate}</td>

                                <td>{leave.endDate}</td>

                                <td>{leave.reason}</td>

                                <td>

                                        <span
                                            className={`badge ${
                                                leave.status === "APPROVED"
                                                    ? "bg-success"
                                                    : leave.status === "REJECTED"
                                                        ? "bg-danger"
                                                        : "bg-warning text-dark"
                                            }`}
                                        >
                                            {leave.status}
                                        </span>

                                </td>

                            </tr>

                        ))}

                        </tbody>

                    </table>

                </div>

            </div>

        </div>
            </EmployeeLayout>

    );

}

export default MyLeaves;