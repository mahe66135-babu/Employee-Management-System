import { useState } from "react";
import { verifyOtp } from "../../services/authService";
import { useLocation, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

function VerifyOtp() {

    const [otp, setOtp] = useState("");

    const navigate = useNavigate();

    const location = useLocation();

    const email = location.state?.email;

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await verifyOtp(email, otp);

            Swal.fire(
                "Success",
                "OTP Verified",
                "success"
            );

            navigate("/reset-password", {
                state: {
                    email,
                    otp
                }
            });

        } catch (error) {

            Swal.fire(
                "Error",
                error.response?.data || "Invalid OTP",
                "error"
            );

        }

    };

    return (

        <div className="container mt-5">

            <div className="card col-md-5 mx-auto shadow">

                <div className="card-body">

                    <h3 className="text-center mb-4">

                        Verify OTP

                    </h3>

                    <form onSubmit={handleSubmit}>

                        <input
                            type="text"
                            className="form-control mb-3"
                            placeholder="Enter OTP"
                            value={otp}
                            onChange={(e) =>
                                setOtp(e.target.value)
                            }
                            required
                        />

                        <button
                            className="btn btn-success w-100"
                        >
                            Verify OTP
                        </button>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default VerifyOtp;