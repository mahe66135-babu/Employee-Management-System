import API from "../api/axiosConfig";

export const getDepartments = () =>
    API.get("/departments");

export const addDepartment = (data) =>
    API.post("/departments", data);

export const updateDepartment = (id, data) =>
    API.put(`/departments/${id}`, data);

export const deleteDepartment = (id) =>
    API.delete(`/departments/${id}`);