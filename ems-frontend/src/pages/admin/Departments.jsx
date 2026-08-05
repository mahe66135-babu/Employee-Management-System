import { useEffect, useState } from "react";
import AdminLayout from "../../components/layouts/AdminLayout";
import {
    getDepartments,
    deleteDepartment
} from "../../services/departmentService";
import { FaEye, FaEdit, FaTrash } from "react-icons/fa";
import DepartmentForm from "../../components/common/DepartmentForm";
import Swal from "sweetalert2";

function Departments() {

    const [departments, setDepartments] = useState([]);
    const [search, setSearch] = useState("");
    const [showModal, setShowModal] = useState(false);

    const [selectedDepartment, setSelectedDepartment] = useState(null);
    const [showViewModal, setShowViewModal] = useState(false);
    const [viewDepartment, setViewDepartment] = useState(null);

    const loadDepartments = async () => {

        try {

            const response = await getDepartments();

            setDepartments(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadDepartments();

    }, []);

    const handleDelete = async (id) => {

        const result = await Swal.fire({

            title: "Delete Department?",

            text: "This action cannot be undone.",

            icon: "warning",

            showCancelButton: true,

            confirmButtonColor: "#d33",

            confirmButtonText: "Yes"

        });

        if (result.isConfirmed) {

            try {

                await deleteDepartment(id);

                Swal.fire(
                    "Deleted!",
                    "Department deleted successfully.",
                    "success"
                );

                loadDepartments();

            } catch (error) {

                console.error(error);

                Swal.fire(
                    "Error",
                    "Unable to delete department.",
                    "error"
                );

            }

        }

    };

    const filteredDepartments = departments.filter(department =>
        department.departmentName
            .toLowerCase()
            .includes(search.toLowerCase())
    );

    return (

        <AdminLayout>

            <div className="card shadow">

                <div className="card-header d-flex justify-content-between">

                    <h3>Departments</h3>

                    <button
                        className="btn btn-primary"
                        onClick={() => {

                            setSelectedDepartment(null);

                            setShowModal(true);

                        }}
                    >

                        + Add Department

                    </button>

                </div>

                <div className="card-body">

                    <input
                        className="form-control mb-3"
                        placeholder="Search Department..."
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                    />

                    <table className="table table-hover">

                        <thead className="table-dark">

                        <tr>

                            <th>ID</th>
                            <th>Department</th>
                            <th>Location</th>
                            <th>Actions</th>

                        </tr>

                        </thead>

                        <tbody>

                        {filteredDepartments.map((department) => (

                            <tr key={department.id}>

                                <td>{department.id}</td>

                                <td>{department.departmentName}</td>

                                <td>{department.location}</td>

                                <td>

                                    <button
                                        className="btn btn-info btn-sm me-2"
                                        onClick={() => {

                                            setViewDepartment(department);

                                            setShowViewModal(true);

                                        }}
                                    >
                                        <FaEye />
                                    </button>

                                    <button
                                        className="btn btn-warning btn-sm me-2"
                                        onClick={() => {

                                            setSelectedDepartment(department);

                                            setShowModal(true);

                                        }}
                                    >

                                        <FaEdit />

                                    </button>

                                    <button
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleDelete(department.id)}
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
            {showModal && (

                <div
                    className="modal d-block"
                    style={{ background: "rgba(0,0,0,0.5)" }}
                >

                    <div className="modal-dialog">

                        <div className="modal-content">

                            <div className="modal-header">

                                <h5>

                                    {selectedDepartment
                                        ? "Edit Department"
                                        : "Add Department"}

                                </h5>

                                <button
                                    className="btn-close"
                                    onClick={() => setShowModal(false)}
                                />

                            </div>

                            <div className="modal-body">

                                <DepartmentForm
                                    departmentData={selectedDepartment}
                                    onSuccess={() => {

                                        setShowModal(false);

                                        setSelectedDepartment(null);

                                        loadDepartments();

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

                                <h5>Department Details</h5>

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

                                        <td>{viewDepartment?.id}</td>

                                    </tr>

                                    <tr>

                                        <th>Department</th>

                                        <td>{viewDepartment?.departmentName}</td>

                                    </tr>

                                    <tr>

                                        <th>Location</th>

                                        <td>{viewDepartment?.location}</td>

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

export default Departments;