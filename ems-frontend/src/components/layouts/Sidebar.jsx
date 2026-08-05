import { Link } from "react-router-dom";

function Sidebar() {

    return (

        <div
            className="bg-dark text-white p-3"
            style={{
                width: "250px",
                minHeight: "100vh"
            }}
        >

            <h4 className="mb-4">

                Admin Panel

            </h4>

            <ul className="nav flex-column">

                <li className="nav-item mb-3">

                    <Link
                        to="/admin/dashboard"
                        className="nav-link text-white"
                    >
                        Dashboard
                    </Link>

                </li>

                <li className="nav-item mb-3">

                    <Link
                        to="/admin/employees"
                        className="nav-link text-white"
                    >
                        Employees
                    </Link>

                </li>

                <li className="nav-item mb-3">

                    <Link
                        to="/admin/departments"
                        className="nav-link text-white"
                    >
                        Departments
                    </Link>

                </li>

                <li className="nav-item mb-3">

                    <Link
                        to="/admin/leaves"
                        className="nav-link text-white"
                    >
                        Leaves
                    </Link>

                </li>

                <li className="nav-item">

                    <Link
                        to="/admin/performance"
                        className="nav-link text-white"
                    >
                        Performance
                    </Link>

                </li>
                <li className="nav-item">

                    <Link
                        to="/admin/attendance"
                        className="nav-link text-white"
                    >
                        Attendance
                    </Link>

                </li>
                <li className="nav-item">
                    <Link
                        to="/admin/payroll"
                        className="nav-link text-white"
                    >
                        Payroll
                    </Link>
                </li>
                <li className="nav-item mb-3">

                    <Link
                        to="/admin/change-password"
                        className="nav-link text-white"
                    >
                        Change Password
                    </Link>

                </li>

            </ul>

        </div>

    );
}

export default Sidebar;