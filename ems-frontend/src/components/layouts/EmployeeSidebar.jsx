import { Link } from "react-router-dom";

function EmployeeSidebar() {

    return (

        <div
            className="bg-secondary text-white p-3"
            style={{
                width: "250px",
                minHeight: "100vh"
            }}
        >

            <h4 className="mb-4">

                Employee

            </h4>

            <ul className="nav flex-column">

                <li className="nav-item mb-3">

                    <Link
                        to="/employee/dashboard"
                        className="nav-link text-white"
                    >
                        Dashboard
                    </Link>

                </li>

                <li className="nav-item mb-3">

                    <Link
                        to="/employee/profile"
                        className="nav-link text-white"
                    >
                        My Profile
                    </Link>

                </li>

                <li className="nav-item mb-3">

                    <Link
                        to="/employee/leaves"
                        className="nav-link text-white"
                    >
                        My Leaves
                    </Link>

                </li>

                <li className="nav-item">

                    <Link
                        to="/employee/performance"
                        className="nav-link text-white"
                    >
                        My Performance
                    </Link>


                </li>
                <li className="nav-item">

                    <Link
                        to="/employee/payroll"
                        className="nav-link text-white"
                    >
                        My Payroll
                    </Link>

                </li>
                <li className="nav-item">

                    <Link
                        to="/employee/attendance"
                        className="nav-link text-white"
                    >
                        My Attendance
                    </Link>

                </li>
                <li className="nav-item mb-3">

                    <Link
                        to="/employee/change-password"
                        className="nav-link text-white"
                    >
                        Change Password
                    </Link>

                </li>

            </ul>

        </div>

    );

}

export default EmployeeSidebar;