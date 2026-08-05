import API from "../api/axiosConfig";

export const getPayrolls = () => {
    return API.get("/payroll");
};

export const getPayrollById = (id) => {
    return API.get(`/payroll/${id}`);
};

export const generatePayroll = (data) => {
    return API.post("/payroll", data);
};

export const deletePayroll = (id) => {
    return API.delete(`/payroll/${id}`);
};

export const getMyPayroll = () => {
    return API.get("/payroll/me");
};
export const downloadSalarySlip = (id) => {

    return API.get(
        `/payroll/${id}/pdf`,
        {
            responseType: "blob"
        }
    );

};