import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    ResponsiveContainer,
    CartesianGrid
} from "recharts";

function DashboardChart({ dashboard }) {

    const data = [

        {
            name: "Employees",
            count: dashboard.totalEmployees
        },

        {
            name: "Departments",
            count: dashboard.totalDepartments
        },

        {
            name: "Users",
            count: dashboard.totalUsers
        },

        {
            name: "Admins",
            count: dashboard.totalAdmins
        }

    ];

    return (

        <div className="card shadow mt-4">

            <div className="card-header">

                <h5>Company Statistics</h5>

            </div>

            <div className="card-body">

                <ResponsiveContainer
                    width="100%"
                    height={350}
                >

                    <BarChart data={data}>

                        <CartesianGrid strokeDasharray="3 3" />

                        <XAxis dataKey="name" />

                        <YAxis />

                        <Tooltip />

                        <Bar
                            dataKey="count"
                            fill="#0d6efd"
                        />

                    </BarChart>

                </ResponsiveContainer>

            </div>

        </div>

    );

}

export default DashboardChart;