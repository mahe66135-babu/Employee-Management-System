import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import API from "../api/axiosConfig";

function Register() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        confirmPassword: ""
    });

    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);
    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setLoading(true);
        setError("");
        setSuccess("");

        if (formData.password !== formData.confirmPassword) {
            setError("Passwords do not match");
            setLoading(false);
            return;
        }

        try {
            await API.post("/auth/register", {
                name: `${formData.firstName} ${formData.lastName}`,
                email: formData.email,
                password: formData.password
            });

            setSuccess("Registration Successful!");
            setLoading(false);

            setTimeout(() => {
                navigate("/login");
            }, 1500);

        } catch (err) {
            setLoading(false);
            setError(err.response?.data?.message || "Registration Failed");
        }
    };

    return (
        <div className="container vh-100  d-flex align-items-center justify-content-center">
        <div className="card w-50 shadow-lg border-0 rounded-4">

                        <div className="card-body p-5">

                            <h3 className="text-center mb-4">
                                Register
                            </h3>

                            {error && (
                                <div className="alert alert-danger">
                                    {error}
                                </div>
                            )}

                            {success && (
                                <div className="alert alert-success">
                                    {success}
                                </div>
                            )}

                            <form onSubmit={handleSubmit}>

                                <div className="mb-3">
                                    <label>First Name</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        name="firstName"
                                        value={formData.firstName}
                                        onChange={handleChange}
                                        required
                                    />
                                </div>

                                <div className="mb-3">
                                    <label>Last Name</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        name="lastName"
                                        value={formData.lastName}
                                        onChange={handleChange}
                                        required
                                    />
                                </div>

                                <div className="mb-3">
                                    <label>Email</label>
                                    <input
                                        type="email"
                                        className="form-control"
                                        name="email"
                                        value={formData.email}
                                        onChange={handleChange}
                                        required
                                    />
                                </div>

                                <div className="mb-3">
                                    <label>Password</label>

                                    <div className="input-group">

                                        <input
                                            type={showPassword ? "text" : "password"}
                                            className="form-control"
                                            name="password"
                                            value={formData.password}
                                            onChange={handleChange}
                                            required
                                        />
                                        {formData.password.length > 0 && (
                                            <small
                                                className={
                                                    formData.password.length >= 8
                                                        ? "text-success"
                                                        : "text-danger"
                                                }
                                            >
                                                {formData.password.length >= 8
                                                    ? "Strong Password"
                                                    : "Minimum 8 characters"}
                                            </small>
                                        )}

                                        <button
                                            type="button"
                                            className="btn btn-outline-secondary"
                                            onClick={() => setShowPassword(!showPassword)}
                                        >
                                            {showPassword ? <FaEyeSlash /> : <FaEye />}
                                        </button>

                                    </div>

                                </div>

                                <div className="mb-3">
                                    <label>Confirm Password</label>

                                    <div className="input-group">

                                        <input
                                            type={showConfirmPassword ? "text" : "password"}
                                            className="form-control"
                                            name="confirmPassword"
                                            value={formData.confirmPassword}
                                            onChange={handleChange}
                                            required
                                        />

                                        <button
                                            type="button"
                                            className="btn btn-outline-secondary"
                                            onClick={() =>
                                                setShowConfirmPassword(!showConfirmPassword)
                                            }
                                        >
                                            {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
                                        </button>

                                    </div>

                                </div>

                                <button
                                    className="btn btn-primary btn-lg w-100"
                                    disabled={loading}
                                >
                                    {loading ? "Registering..." : "Register"}
                                </button>

                            </form>

                            <div className="text-center mt-3">

                                <p>
                                    Already have an account?
                                    <Link to="/login"> Login</Link>
                                </p>

                            </div>

                        </div>
        </div>


        </div>
    );
}

export default Register;