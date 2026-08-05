import { useState } from "react";
import {
    addDepartment,
    updateDepartment
} from "../../services/departmentService";

function DepartmentForm({ departmentData, onSuccess }) {

    const [department, setDepartment] = useState(

        departmentData || {

            departmentName: "",

            location: ""

        }

    );

    const handleChange = (e) => {

        setDepartment({

            ...department,

            [e.target.name]: e.target.value

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            if (department.id) {

                await updateDepartment(

                    department.id,

                    department

                );

                alert("Department Updated Successfully");

            } else {

                await addDepartment(department);

                alert("Department Added Successfully");

            }

            onSuccess();

        } catch (error) {

            console.error(error);

            alert("Operation Failed");

        }

    };

    return (

        <form onSubmit={handleSubmit}>

            <div className="mb-3">

                <label className="form-label">

                    Department Name

                </label>

                <input

                    className="form-control"

                    name="departmentName"

                    value={department.departmentName}

                    onChange={handleChange}

                />

            </div>

            <div className="mb-3">

                <label className="form-label">

                    Location

                </label>

                <input

                    className="form-control"

                    name="location"

                    value={department.location}

                    onChange={handleChange}

                />

            </div>

            <button

                className="btn btn-success"

            >

                Save Department

            </button>

        </form>

    );

}

export default DepartmentForm;