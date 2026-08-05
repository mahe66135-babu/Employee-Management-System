import { useEffect, useState } from "react";
import AdminLayout from "../../components/layouts/AdminLayout";
import {
    getAllLeaves,
    approveLeave,
    rejectLeave
} from "../../services/leaveService";
import Swal from "sweetalert2";

function Leaves() {

    const [leaves, setLeaves] = useState([]);

    const loadLeaves = async () => {

        try {

            const response = await getAllLeaves();

            setLeaves(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadLeaves();

    }, []);

    const handleApprove = async (id) => {

        try {

            await approveLeave(id);

            Swal.fire(
                "Success",
                "Leave Approved",
                "success"
            );

            loadLeaves();

        } catch (error) {

            console.error(error);

        }

    };

    const handleReject = async (id) => {

        try {

            await rejectLeave(id);

            Swal.fire(
                "Success",
                "Leave Rejected",
                "success"
            );

            loadLeaves();

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <AdminLayout>

            <div className="card shadow">

                <div className="card-header">

                    <h3>Leave Management</h3>

                </div>

                <div className="card-body">

                    <div className="table-responsive">

                        <table className="table table-hover">

                            <thead className="table-dark">

                            <tr>

                                <th>ID</th>
                                <th>Employee</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Reason</th>
                                <th>Status</th>
                                <th>Actions</th>

                            </tr>

                            </thead>

                            <tbody>

                            {leaves.map((leave) => (

                                <tr key={leave.id}>

                                    <td>{leave.id}</td>

                                    <td>{leave.employeeName}</td>

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

                                    <td>

                                        {leave.status === "PENDING" && (

                                            <>

                                                <button
                                                    className="btn btn-success btn-sm me-2"
                                                    onClick={() =>
                                                        handleApprove(leave.id)
                                                    }
                                                >
                                                    Approve
                                                </button>

                                                <button
                                                    className="btn btn-danger btn-sm"
                                                    onClick={() =>
                                                        handleReject(leave.id)
                                                    }
                                                >
                                                    Reject
                                                </button>

                                            </>

                                        )}

                                    </td>

                                </tr>

                            ))}

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>

        </AdminLayout>

    );

}

export default Leaves;