import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/auth/Login";
import Dashboard from "./pages/admin/Dashboard";
import PrivateRoute from "./components/routes/PrivateRoute";
import RoleRoute from "./components/routes/RoleRoute";
import EmployeeDashboard from "./pages/employee/EmployeeDashboard.jsx";
import Employees from "./pages/admin/Employees";
import Departments from "./pages/admin/Departments";
import Leaves from "./pages/admin/Leaves";
import MyLeaves from "./pages/employee/MyLeaves";
import Performance from "./pages/admin/Performance";
import MyPerformance from "./pages/employee/MyPerformance";
import MyProfile from "./pages/employee/MyProfile";
import ChangePassword from "./pages/employee/ChangePassword";

import Payroll from "./pages/admin/Payroll";
import MyPayroll from "./pages/employee/MyPayroll";
import MyAttendance from "./pages/employee/MyAttendance";
import Attendance from "./pages/admin/Attendance";
import VerifyOtp from "./pages/auth/VerifyOtp";
import ResetPassword from "./pages/auth/ResetPassword";
import ForgotPassword from "./pages/auth/ForgotPassword";
import AdminChangePassword from "./pages/admin/AdminChangepassword";
import Register from  "./pages/auth/Register"

function App() {
  return (
      <BrowserRouter>

        <Routes>

          <Route path="/" element={<Login />} />
            <Route
                path="/admin/dashboard"
                element={
                    <PrivateRoute>

                        <RoleRoute role="ADMIN">

                            <Dashboard />

                        </RoleRoute>

                    </PrivateRoute>
                }
            />
            <Route
                path="/employee/dashboard"
                element={
                    <PrivateRoute>

                        <RoleRoute role="EMPLOYEE">

                            <EmployeeDashboard />

                        </RoleRoute>

                    </PrivateRoute>
                }
            />
            <Route
                path="/admin/employees"
                element={
                    <PrivateRoute>
                        <Employees />
                    </PrivateRoute>
                }
            />
            <Route
                path="/admin/departments"
                element={
                    <PrivateRoute>
                        <Departments />
                    </PrivateRoute>
                }
            />
            <Route
                path="/admin/leaves"
                element={
                    <PrivateRoute>
                        <Leaves />
                    </PrivateRoute>
                }
            />
            <Route
                path="/employee/leaves"
                element={
                    <PrivateRoute>
                        <MyLeaves />
                    </PrivateRoute>
                }
            />
            <Route
                path="/admin/performance"
                element={
                    <RoleRoute role="ADMIN">
                        <Performance />
                    </RoleRoute>
                }
            />
            <Route
                path="/employee/performance"
                element={
                    <RoleRoute role="EMPLOYEE">
                        <MyPerformance />
                    </RoleRoute>
                }
            />
            <Route
                path="/employee/profile"
                element={
                    <RoleRoute role="EMPLOYEE">
                        <MyProfile />
                    </RoleRoute>
                }
            />
            <Route
                path="/employee/change-password"
                element={
                    <RoleRoute role="EMPLOYEE">
                        <ChangePassword />
                    </RoleRoute>
                }
            />
            <Route
                path="/admin/change-password"
                element={
                    <RoleRoute role="ADMIN">
                        <AdminChangePassword />
                    </RoleRoute>
                }
            />
            <Route
                path="/admin/payroll"
                element={
                    <RoleRoute role="ADMIN">
                        <Payroll />
                    </RoleRoute>
                }
            />
            <Route
                path="/employee/payroll"
                element={
                    <RoleRoute role="EMPLOYEE">
                        <MyPayroll />
                    </RoleRoute>
                }
            />
            <Route
                path="/employee/attendance"
                element={
                    <RoleRoute role="EMPLOYEE">
                        <MyAttendance />
                    </RoleRoute>
                }
            />
            <Route
                path="/admin/attendance"
                element={
                    <RoleRoute role="ADMIN">
                        <Attendance />
                    </RoleRoute>
                }
            />
            <Route
                path="/verify-otp"
                element={<VerifyOtp />}
            />
            <Route
                path="/reset-password"
                element={<ResetPassword />}
            />
            <Route
                path="/forgot-password"
                element={<ForgotPassword />}
            />
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login />} />


        </Routes>

      </BrowserRouter>
  );
}

export default App;