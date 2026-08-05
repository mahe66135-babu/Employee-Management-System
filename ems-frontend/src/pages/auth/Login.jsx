import { useState } from "react";
import { login } from "../../services/authService";
// import { useAuth } from "../../context/AuthContext";
import useAuth from "../../hooks/useAuth";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import { Link } from "react-router-dom";

function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const {login: saveToken} = useAuth();
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        localStorage.removeItem("token");

        try {

            const response = await login({
                email,
                password
            });

            console.log(response.data);

            // Save JWT
            saveToken(response.data.token);

            // Decode JWT
            const user = jwtDecode(response.data.token);

            console.log(user);

            if (user.role === "ADMIN") {

                navigate("/admin/dashboard");

            } else {

                navigate("/employee/dashboard");

            }

        } catch (error) {

            console.log(error.response?.status);
            console.log(error.response?.data);
            console.error(error);

            alert("Invalid Email or Password");
        }
    };

        return (

            <div className="container mt-5">

                <div className="row justify-content-center">

                    <div className="col-md-4">

                        <div className="card shadow">

                            <div className="card-body">

                                <h3 className="text-center mb-4">
                                    Login
                                </h3>

                                <form onSubmit={handleLogin}>

                                    <input
                                        className="form-control mb-3"
                                        placeholder="Email"
                                        value={email}
                                        onChange={(e) =>
                                            setEmail(e.target.value)
                                        }
                                    />

                                    <input
                                        type="password"
                                        className="form-control mb-3"
                                        placeholder="Password"
                                        value={password}
                                        onChange={(e) =>
                                            setPassword(e.target.value)
                                        }
                                    />
                                    <div className="text-end mb-3">
                                        <Link
                                            to="/forgot-password"
                                            className="text-decoration-none"
                                        >
                                            Forgot Password?
                                        </Link>
                                    </div>

                                    <button
                                        className="btn btn-primary w-100"
                                    >
                                        Login
                                    </button>
                                    <p className="text-center mt-3">
                                        Don't have an account?
                                        <Link to="/register"> Register</Link>
                                    </p>

                                </form>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        );

}

export default Login;