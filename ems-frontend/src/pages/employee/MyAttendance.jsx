import { useEffect, useState } from "react";
import EmployeeLayout from "../../components/layouts/EmployeeLayout";
import {
    checkIn,
    checkOut,
    getMyAttendance
} from "../../services/attendanceService";
import Swal from "sweetalert2";

function MyAttendance() {

    const [attendance, setAttendance] = useState([]);

    const loadAttendance = async () => {

        try {

            const response = await getMyAttendance();

            setAttendance(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        loadAttendance();

    }, []);

    const handleCheckIn = async () => {

        try {

            await checkIn();

            Swal.fire(
                "Success",
                "Checked In Successfully",
                "success"
            );

            loadAttendance();

        } catch (error) {

            Swal.fire(
                "Error",
                error.response?.data?.message || "Check-In Failed",
                "error"
            );

        }

    };

    const handleCheckOut = async () => {

        try {

            await checkOut();

            Swal.fire(
                "Success",
                "Checked Out Successfully",
                "success"
            );

            loadAttendance();

        } catch (error) {

            Swal.fire(
                "Error",
                error.response?.data?.message || "Check-Out Failed",
                "error"
            );

        }

    };

    return (

        <EmployeeLayout>

            <div className="card shadow">

                <div className="card-header d-flex justify-content-between">

                    <h3>My Attendance</h3>

                    <div>

                        <button
                            className="btn btn-success me-2"
                            onClick={handleCheckIn}
                        >
                            Check In
                        </button>

                        <button
                            className="btn btn-danger"
                            onClick={handleCheckOut}
                        >
                            Check Out
                        </button>

                    </div>

                </div>

                <div className="card-body">

                    <div className="table-responsive">

                        <table className="table table-bordered">

                            <thead className="table-dark">

                            <tr>

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
                                        colSpan="5"
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

        </EmployeeLayout>

    );

}

export default MyAttendance;