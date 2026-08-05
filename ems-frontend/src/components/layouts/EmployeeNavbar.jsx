import useAuth from "../../hooks/useAuth";

function EmployeeNavbar() {

    const { user, logout } = useAuth();

    return (

        <nav className="navbar navbar-dark bg-secondary px-4">

            <span className="navbar-brand">

                Employee Portal

            </span>

            <div className="d-flex align-items-center">

                <span className="text-white me-3">

                    {user?.sub}

                </span>

                <button
                    className="btn btn-danger btn-sm"
                    onClick={logout}
                >
                    Logout
                </button>

            </div>

        </nav>

    );

}

export default EmployeeNavbar;