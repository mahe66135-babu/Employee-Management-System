import { useEffect, useState } from "react";
import { getEmployees } from "../../services/employeeService";
import {
    addPerformance,
    updatePerformance
} from "../../services/performanceService";
import Swal from "sweetalert2";

function PerformanceForm({ performanceData, onSuccess }) {

    const [employees, setEmployees] = useState([]);

    const [performance, setPerformance] = useState(
        performanceData || {
            employeeId: "",
            rating: "",
            remarks: ""
        }
    );

    useEffect(() => {
        loadEmployees();
    }, []);

    const loadEmployees = async () => {
        try {
            const response = await getEmployees();
            setEmployees(response.data.content);
        } catch (error) {
            console.error(error);
        }
    };

    const handleChange = (e) => {
        setPerformance({
            ...performance,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            if (performance.id) {

                await updatePerformance(
                    performance.id,
                    performance
                );

                Swal.fire(
                    "Updated",
                    "Performance updated successfully",
                    "success"
                );

            } else {

                await addPerformance(performance);

                Swal.fire(
                    "Success",
                    "Performance added successfully",
                    "success"
                );

            }

            onSuccess();

        } catch (error) {

            console.error(error);

            Swal.fire(
                "Error",
                "Operation Failed",
                "error"
            );

        }

    };

    return (

        <form onSubmit={handleSubmit}>

            <div className="mb-3">

                <label>Employee</label>

                <select
                    className="form-select"
                    name="employeeId"
                    value={performance.employeeId}
                    onChange={handleChange}
                    required
                >

                    <option value="">
                        Select Employee
                    </option>

                    {employees.map(employee => (

                        <option
                            key={employee.id}
                            value={employee.id}
                        >
                            {employee.firstName} {employee.lastName}
                        </option>

                    ))}

                </select>

            </div>

            <div className="mb-3">

                <label>Rating</label>

                <select
                    className="form-select"
                    name="rating"
                    value={performance.rating}
                    onChange={handleChange}
                    required
                >

                    <option value="">Select Rating</option>
                    <option value="5">★★★★★ Excellent</option>
                    <option value="4">★★★★ Very Good</option>
                    <option value="3">★★★ Good</option>
                    <option value="2">★★ Average</option>
                    <option value="1">★ Poor</option>

                </select>

            </div>

            <div className="mb-3">

                <label>Remarks</label>

                <textarea
                    className="form-control"
                    rows="4"
                    name="remarks"
                    value={performance.remarks}
                    onChange={handleChange}
                    required
                />

            </div>

            <button className="btn btn-success">

                Save Performance

            </button>

        </form>

    );

}

export default PerformanceForm;