import { useState } from "react";
import { uploadProfilePhoto } from "../../services/employeeService";
import Swal from "sweetalert2";

function ProfilePhotoUpload({ employeeId, onSuccess }) {

    const [file, setFile] = useState(null);

    const handleUpload = async () => {

        if (!file) {

            Swal.fire(
                "Error",
                "Please select an image",
                "error"
            );

            return;

        }

        try {

            await uploadProfilePhoto(
                employeeId,
                file
            );

            Swal.fire(
                "Success",
                "Profile photo uploaded successfully",
                "success"
            );

            onSuccess();

        } catch (error) {

            Swal.fire(
                "Error",
                "Upload failed",
                "error"
            );

        }

    };

    return (

        <div>

            <input
                type="file"
                className="form-control mb-3"
                accept="image/*"
                onChange={(e) =>
                    setFile(e.target.files[0])
                }
            />

            <button
                className="btn btn-primary"
                onClick={handleUpload}
            >
                Upload Photo
            </button>

        </div>

    );

}

export default ProfilePhotoUpload;