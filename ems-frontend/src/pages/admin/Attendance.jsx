import { useEffect, useState } from "react";
import AdminLayout from "../../components/layouts/AdminLayout";
import {
    getAllAttendance,
    getAttendanceByDate
} from "../../services/attendanceService";

function Attendance() {

    const [attendance, setAttendance] = useState([]);
    const [date, setDate] = useState("");

    const loadAttendance = async () => {

        try {

            const response = await getAllAttendance();

            setAttendance(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadAttendance();

    }, []);

    const searchByDate = async () => {

        if (!date) {

            loadAttendance();

            return;

        }

        try {

            const response =
                await getAttendanceByDate(date);

            setAttendance(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <AdminLayout>

            <div className="card shadow">

                <div className="card-header d-flex justify-content-between align-items-center">

                    <h3>Attendance Management</h3>

                    <div className="d-flex">

                        <input
                            type="date"
                            className="form-control me-2"
                            value={date}
                            onChange={(e) =>
                                setDate(e.target.value)
                            }
                        />

                        <button
                            className="btn btn-primary"
                            onClick={searchByDate}
                        >
                            Search
                        </button>

                    </div>

                </div>

                <div className="card-body">

                    <div className="table-responsive">

                        <table className="table table-bordered table-hover">

                            <thead className="table-dark">

                            <tr>

                                <th>ID</th>
                                <th>Employee</th>
                                <th>Date</th>
                                <th>Check In</th>
                                <th>Check Out</th>
                                <th>Status</th>
                                <th>Working Hours</th>

                            </tr>

                            </thead>

                            <tbody>

                            {attendance.length > 0 ? (

                                attendance.map(item => (

                                    <tr key={item.id}>

                                        <td>{item.id}</td>
                                        <td>{item.employeeName}</td>
                                        <td>{item.attendanceDate}</td>
                                        <td>{item.checkIn}</td>
                                        <td>{item.checkOut}</td>
                                        <td>{item.status}</td>
                                        <td>{item.workingHours}</td>

                                    </tr>

                                ))

                            ) : (

                                <tr>

                                    <td
                                        colSpan="7"
                                        className="text-center"
                                    >
                                        No Attendance Found
                                    </td>

                                </tr>

                            )}

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>

        </AdminLayout>

    );

}

export default Attendance;