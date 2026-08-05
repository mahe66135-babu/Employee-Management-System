import API from "../api/axiosConfig";

export const applyLeave = (data) =>
    API.post("/leaves", data);

export const getMyLeaves = () =>
    API.get("/leaves/me");

export const getAllLeaves = () =>
    API.get("/leaves");

export const approveLeave = (id) =>
    API.put(`/leaves/${id}/approve`);

export const rejectLeave = (id) =>
    API.put(`/leaves/${id}/reject`);

export const getLeaveStatistics = () =>
    API.get("/leaves/statistics");

export const getLeavesByStatus = (status) =>
    API.get(`/leaves/status?status=${status}`);