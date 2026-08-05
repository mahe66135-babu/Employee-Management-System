import { useEffect, useState } from "react";
import AdminLayout from "../../components/layouts/AdminLayout";
import PerformanceForm from "../../components/common/PerformanceForm";
import {
    getPerformances,
    deletePerformance
} from "../../services/performanceService";
import { FaEdit, FaTrash } from "react-icons/fa";
import Swal from "sweetalert2";

function Performance() {

    const [performances, setPerformances] = useState([]);
    const [search, setSearch] = useState("");
    const [showModal, setShowModal] = useState(false);
    const [selectedPerformance, setSelectedPerformance] = useState(null);

    const loadPerformances = async () => {

        try {

            const response = await getPerformances();

            setPerformances(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadPerformances();

    }, []);

    const handleDelete = async (id) => {

        const result = await Swal.fire({

            title: "Delete Review?",

            text: "This action cannot be undone.",

            icon: "warning",

            showCancelButton: true,

            confirmButtonColor: "#d33",

            confirmButtonText: "Delete"

        });

        if (result.isConfirmed) {

            try {

                await deletePerformance(id);

                Swal.fire(
                    "Deleted",
                    "Review deleted successfully",
                    "success"
                );

                loadPerformances();

            } catch (error) {

                console.error(error);

                Swal.fire(
                    "Error",
                    "Unable to delete review",
                    "error"
                );

            }

        }

    };

    const filtered = performances.filter(item =>
        item.employeeName.toLowerCase().includes(search.toLowerCase())
    );

    return (

        <AdminLayout>

            <div className="card shadow">

                <div className="card-header d-flex justify-content-between">

                    <h3>Performance Reviews</h3>

                    <button
                        className="btn btn-primary"
                        onClick={() => {

                            setSelectedPerformance(null);

                            setShowModal(true);

                        }}
                    >
                        + Add Review
                    </button>

                </div>

                <div className="card-body">

                    <input
                        className="form-control mb-3"
                        placeholder="Search Employee..."
                        value={search}
                        onChange={(e) =>
                            setSearch(e.target.value)
                        }
                    />

                    <div className="table-responsive">

                        <table className="table table-hover">

                            <thead className="table-dark">

                            <tr>

                                <th>Employee</th>
                                <th>Rating</th>
                                <th>Remarks</th>
                                <th>Date</th>
                                <th>Actions</th>

                            </tr>

                            </thead>

                            <tbody>

                            {filtered.map(item => (

                                <tr key={item.id}>

                                    <td>{item.employeeName}</td>

                                    <td>

                                        {"★".repeat(item.rating)}

                                    </td>

                                    <td>{item.remarks}</td>

                                    <td>{item.reviewDate}</td>

                                    <td>

                                        <button
                                            className="btn btn-warning btn-sm me-2"
                                            onClick={() => {

                                                setSelectedPerformance(item);

                                                setShowModal(true);

                                            }}
                                        >
                                            <FaEdit />
                                        </button>

                                        <button
                                            className="btn btn-danger btn-sm"
                                            onClick={() =>
                                                handleDelete(item.id)
                                            }
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
                    style={{
                        background: "rgba(0,0,0,.5)"
                    }}
                >

                    <div className="modal-dialog">

                        <div className="modal-content">

                            <div className="modal-header">

                                <h5>

                                    {selectedPerformance
                                        ? "Edit Performance"
                                        : "Add Performance"}

                                </h5>

                                <button
                                    className="btn-close"
                                    onClick={() =>
                                        setShowModal(false)
                                    }
                                />

                            </div>

                            <div className="modal-body">

                                <PerformanceForm
                                    performanceData={selectedPerformance}
                                    onSuccess={() => {

                                        setShowModal(false);

                                        loadPerformances();

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

export default Performance;