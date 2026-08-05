import { useState } from "react";
import { resetPassword } from "../../services/authService";
import { useLocation, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

function ResetPassword() {

    const [newPassword, setNewPassword] = useState("");

    const navigate = useNavigate();

    const location = useLocation();

    const email = location.state?.email;
    const otp = location.state?.otp;

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await resetPassword(
                email,
                otp,
                newPassword
            );

            Swal.fire({
                icon: "success",
                title: "Success",
                text: "Password changed successfully"
            });

            navigate("/login");

        } catch (error) {

            Swal.fire({
                icon: "error",
                title: "Error",
                text: error.response?.data || "Password reset failed"
            });

        }

    };

    return (

        <div className="container mt-5">

            <div className="card shadow col-md-5 mx-auto">

                <div className="card-body">

                    <h3 className="text-center mb-4">

                        Reset Password

                    </h3>

                    <form onSubmit={handleSubmit}>

                        <div className="mb-3">

                            <label className="form-label">
                                New Password
                            </label>

                            <input
                                type="password"
                                className="form-control"
                                value={newPassword}
                                onChange={(e) =>
                                    setNewPassword(e.target.value)
                                }
                                required
                            />

                        </div>

                        <button
                            className="btn btn-primary w-100"
                        >
                            Reset Password
                        </button>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default ResetPassword;