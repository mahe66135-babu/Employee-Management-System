import { useEffect, useState } from "react";
import EmployeeLayout from "../../components/layouts/EmployeeLayout";
import { getMyProfile } from "../../services/profileService";

function EmployeeDashboard() {

    const [employee, setEmployee] = useState(null);

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

            <h2 className="mb-4">

                Employee Dashboard

            </h2>

            <div className="row">

                <div className="col-md-6">

                    <div className="card shadow">

                        <div className="card-body">

                            <h4>

                                Welcome,

                                {" "}

                                {employee?.firstName}

                            </h4>

                            <hr />

                            <p>

                                <strong>Email:</strong>

                                {" "}

                                {employee?.email}

                            </p>

                            <p>

                                <strong>Department:</strong>

                                {" "}

                                {employee?.departmentName}

                            </p>

                            <p>

                                <strong>Designation:</strong>

                                {" "}

                                {employee?.designation}

                            </p>

                        </div>

                    </div>

                </div>

            </div>

        </EmployeeLayout>

    );

}

export default EmployeeDashboard;