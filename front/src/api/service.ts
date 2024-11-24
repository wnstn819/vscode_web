import axios from "axios";
import { Employee, Login } from "./type";

export const getEmployeeList = async () => {
  const response = await axios.get("http://localhost:8080/api/list");
  return response.data;
};

export const postLogin = async (data: Login) => {
  const response = await axios.post("http://localhost:8080/api/login", {
    name: data.name,
    password: data.password,
  });
  return response.data;
};

export const postJoin = async (data: Login) => {
  console.log("왜 여기 안옴");

  const response = await axios.post("http://localhost:8080/api/user", {
    name: data.name,
    password: data.password,
  });
  return response.data;
};

export const delEmployee = async (userId: number) => {
  const response = await axios.delete(
    `http://localhost:8080/api/user/${userId}`
  );
  return response.data;
};

export const patchEmployee = async (
  userId: number,
  updates: Partial<Employee>
) => {
  const response = await axios.patch(
    `http://localhost:8080/api/user/${userId}`,
    {
      updates: updates,
    }
  );
  return response.data;
};

export const joinEmployee = async (data: Employee) => {
  const response = await axios.post(`http://localhost:8080/api/user/`, {
    data,
  });

  return response.data;
};
