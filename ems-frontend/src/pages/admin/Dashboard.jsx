import { useEffect, useState } from "react";
import AdminLayout from "../../components/layouts/AdminLayout";
import { getDashboard } from "../../services/dashboardService";
import DashboardChart from "../../components/common/DashboardChart";
import RecentEmployees from "../../components/common/RecentEmployees";

function Dashboard() {

    const [dashboard, setDashboard] = useState({
        totalEmployees: 0,
        totalDepartments: 0,
        totalUsers: 0,
        totalAdmins: 0
    });

    useEffect(() => {
        const loadDashboard = async () => {
            try {
                const response = await getDashboard();

                console.log("Dashboard Response:", response.data);

                setDashboard(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        loadDashboard();
    }, []);




    return (
        <AdminLayout>

            <h2 className="mb-4">
                Admin Dashboard
            </h2>
            <div className="row">

                <div className="col-md-3">
                    <div className="card bg-primary text-white shadow">
                        <div className="card-body">
                            <h5>Total Employees</h5>
                            <h2>{dashboard.totalEmployees}</h2>
                        </div>
                    </div>
                </div>

                <div className="col-md-3">
                    <div className="card bg-success text-white shadow">
                        <div className="card-body">
                            <h5>Total Departments</h5>
                            <h2>{dashboard.totalDepartments}</h2>
                        </div>
                    </div>
                </div>

                <div className="col-md-3">
                    <div className="card bg-warning shadow">
                        <div className="card-body">
                            <h5>Total Users</h5>
                            <h2>{dashboard.totalUsers}</h2>
                        </div>
                    </div>
                </div>

                <div className="col-md-3">
                    <div className="card bg-danger text-white shadow">
                        <div className="card-body">
                            <h5>Total Admins</h5>
                            <h2>{dashboard.totalAdmins}</h2>
                        </div>
                    </div>
                </div>

            </div>

            <DashboardChart dashboard={dashboard} />
            <RecentEmployees />
        </AdminLayout>
    );
}

export default Dashboard;


