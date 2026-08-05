import { useState } from "react";
import { applyLeave } from "../../services/leaveService";
import Swal from "sweetalert2";

function LeaveForm({ onSuccess }) {

    const [leave, setLeave] = useState({
        startDate: "",
        endDate: "",
        reason: ""
    });

    const handleChange = (e) => {

        setLeave({
            ...leave,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await applyLeave(leave);

            Swal.fire(
                "Success",
                "Leave Applied Successfully",
                "success"
            );

            onSuccess();

        } catch (error) {

            console.error(error);

            Swal.fire(
                "Error",
                "Unable to Apply Leave",
                "error"
            );

        }

    };

    return (

        <form onSubmit={handleSubmit}>

            <div className="mb-3">

                <label>Start Date</label>

                <input
                    type="date"
                    className="form-control"
                    name="startDate"
                    value={leave.startDate}
                    onChange={handleChange}
                    required
                />

            </div>

            <div className="mb-3">

                <label>End Date</label>

                <input
                    type="date"
                    className="form-control"
                    name="endDate"
                    value={leave.endDate}
                    onChange={handleChange}
                    required
                />

            </div>

            <div className="mb-3">

                <label>Reason</label>

                <textarea
                    className="form-control"
                    rows="4"
                    name="reason"
                    value={leave.reason}
                    onChange={handleChange}
                    required
                />

            </div>

            <button className="btn btn-primary">

                Apply Leave

            </button>

        </form>

    );

}

export default LeaveForm;