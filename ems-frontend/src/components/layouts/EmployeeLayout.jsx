import EmployeeNavbar from "./EmployeeNavbar";
import EmployeeSidebar from "./EmployeeSidebar";

function EmployeeLayout({ children }) {

    return (

        <>

            <EmployeeNavbar />

            <div className="d-flex">

                <EmployeeSidebar />

                <div className="container-fluid p-4">

                    {children}

                </div>

            </div>

        </>

    );

}

export default EmployeeLayout;