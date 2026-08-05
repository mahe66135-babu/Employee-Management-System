import API from "../api/axiosConfig";

export const getEmployees = () => {
    return API.get("/employees");
};

export const getEmployeeById = (id) => {
    return API.get(`/employees/${id}`);
};

export const addEmployee = (employee) => {
    return API.post("/employees", employee);
};

export const updateEmployee = (id, employee) => {
    return API.put(`/employees/${id}`, employee);
};

export const deleteEmployee = (id) => {
    return API.delete(`/employees/${id}`);
};
export const getRecentEmployees = () =>
    API.get("/employees/recent");
export const exportEmployeesExcel = async () => {

    const response = await API.get(
        "/export/employees/excel",
        {
            responseType: "blob"
        }
    );

    return response;

};
export const exportEmployeesPdf = async () => {

    return API.get(
        "/export/employees/pdf",
        {
            responseType: "blob"
        }
    );

};
export const uploadProfilePhoto = (id, file) => {

    const formData = new FormData();

    formData.append("file", file);

    return API.post(
        `/employees/${id}/photo`,
        formData,
        {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        }
    );

};