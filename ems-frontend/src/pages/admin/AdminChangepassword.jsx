import { useState } from "react";
import AdminLayout from "../../components/layouts/AdminLayout";
import { changePassword } from "../../services/authService";
import Swal from "sweetalert2";

function AdminChangePassword() {

    const [form, setForm] = useState({
        currentPassword: "",
        newPassword: "",
        confirmPassword: ""
    });

    const handleChange = (e) => {

        setForm({
            ...form,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        if (form.newPassword !== form.confirmPassword) {

            Swal.fire(
                "Error",
                "Passwords do not match",
                "error"
            );

            return;
        }

        try {

            await changePassword({
                currentPassword: form.currentPassword,
                newPassword: form.newPassword
            });

            Swal.fire(
                "Success",
                "Password changed successfully",
                "success"
            );

            setForm({
                currentPassword: "",
                newPassword: "",
                confirmPassword: ""
            });

        } catch (error) {

            console.error(error);

            Swal.fire(
                "Error",
                "Unable to change password",
                "error"
            );

        }

    };

    return (

        <AdminLayout>

            <div className="card shadow">

                <div className="card-header">

                    <h3>Change Password</h3>

                </div>

                <div className="card-body">

                    <form onSubmit={handleSubmit}>

                        <div className="mb-3">

                            <label>Current Password</label>

                            <input
                                type="password"
                                className="form-control"
                                name="currentPassword"
                                value={form.currentPassword}
                                onChange={handleChange}
                                required
                            />

                        </div>

                        <div className="mb-3">

                            <label>New Password</label>

                            <input
                                type="password"
                                className="form-control"
                                name="newPassword"
                                value={form.newPassword}
                                onChange={handleChange}
                                required
                            />

                        </div>

                        <div className="mb-3">

                            <label>Confirm Password</label>

                            <input
                                type="password"
                                className="form-control"
                                name="confirmPassword"
                                value={form.confirmPassword}
                                onChange={handleChange}
                                required
                            />

                        </div>

                        <button className="btn btn-primary">

                            Change Password

                        </button>

                    </form>

                </div>

            </div>

        </AdminLayout>

    );

}

export default AdminChangePassword;