import API from "../api/axiosConfig";

export const checkIn = () => {
    return API.post("/attendance/check-in");
};

export const checkOut = () => {
    return API.put("/attendance/check-out");
};

export const getMyAttendance = () => {
    return API.get("/attendance/me");
};

export const getAllAttendance = () => {
    return API.get("/attendance");
};

export const getAttendanceByDate = (date) => {
    return API.get(`/attendance/date?date=${date}`);
};