import { useEffect, useState } from "react";
import EmployeeLayout from "../../components/layouts/EmployeeLayout";
import { getMyProfile } from "../../services/profileService";
import ProfilePhotoUpload from "../../components/common/ProfilePhotoUpload";

function MyProfile() {

    const [employee, setEmployee] = useState({});

    useEffect(() => {
        loadProfile();
    }, []);

    const loadProfile = async () => {

        try {

            const response = await getMyProfile();

            setEmployee(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <EmployeeLayout>

            <div className="card shadow">

                <div className="card-header">

                    <h3>My Profile</h3>

                </div>

                <div className="card-body">
                    <div className="text-center mb-4">

                        <img
                            src={
                                employee.profilePhoto
                                    ? `http://localhost:8080/uploads/profiles/${employee.profilePhoto}`
                                    : "https://via.placeholder.com/150"
                            }
                            alt="Profile"
                            className="rounded-circle border"
                            width="150"
                            height="150"
                        />

                        <div className="mt-3">

                            <ProfilePhotoUpload
                                employeeId={employee.id}
                                onSuccess={loadProfile}
                            />

                        </div>

                    </div>

                    <div className="row">

                        <div className="col-md-6">

                            <p>
                                <strong>First Name:</strong>
                                {" "}
                                {employee.firstName}
                            </p>

                            <p>
                                <strong>Last Name:</strong>
                                {" "}
                                {employee.lastName}
                            </p>

                            <p>
                                <strong>Email:</strong>
                                {" "}
                                {employee.email}
                            </p>

                            <p>
                                <strong>Phone:</strong>
                                {" "}
                                {employee.phone}
                            </p>

                        </div>

                        <div className="col-md-6">

                            <p>
                                <strong>Department:</strong>
                                {" "}
                                {employee.departmentName}
                            </p>

                            <p>
                                <strong>Designation:</strong>
                                {" "}
                                {employee.designation}
                            </p>

                            <p>
                                <strong>Salary:</strong>
                                {" "}
                                ₹{employee.salary}
                            </p>

                            <p>
                                <strong>Joining Date:</strong>
                                {" "}
                                {employee.joiningDate}
                            </p>

                        </div>

                    </div>

                </div>

            </div>

        </EmployeeLayout>

    );

}

export default MyProfile;