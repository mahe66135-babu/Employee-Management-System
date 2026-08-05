import { useState } from "react";
import { forgotPassword } from "../../services/authService";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

function ForgotPassword() {

    const [email, setEmail] = useState("");

    const navigate = useNavigate();

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await forgotPassword(email);

            Swal.fire(
                "Success",
                "OTP Sent Successfully",
                "success"
            );

            navigate("/verify-otp", {
                state: { email }
            });

        } catch (error) {

            Swal.fire(
                "Error",
                error.response?.data || "Failed",
                "error"
            );

        }

    };

    return (

        <div className="container mt-5">

            <div className="card col-md-5 mx-auto">

                <div className="card-body">

                    <h3 className="text-center mb-4">
                        Forgot Password
                    </h3>

                    <form onSubmit={handleSubmit}>

                        <input
                            type="email"
                            className="form-control mb-3"
                            placeholder="Enter Email"
                            value={email}
                            onChange={(e) =>
                                setEmail(e.target.value)
                            }
                        />

                        <button className="btn btn-primary w-100">
                            Send OTP
                        </button>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default ForgotPassword;