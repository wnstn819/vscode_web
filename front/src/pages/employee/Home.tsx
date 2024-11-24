import { useEffect, useState } from "react";
import { Employee } from "../../api/type";
import { delEmployee, getEmployeeList, patchEmployee } from "../../api/service";

export const Home = () => {
  const [employees, setEmployees] = useState<Employee[]>([]);

  const fetchEmployees = async () => {
    try {
      const data = await getEmployeeList();
      setEmployees(data.resultData);
    } catch (e) {
      console.error("Error fetching employee list:", e);
    }
  };

  const deleteEmployee = (userId: number) => {
    delEmployee(userId);
  };

  const patchEmployees = (userId: number) => {
    const updates = {} as Partial<Employee>;
    updates.name = "짱구";
    updates.part = "없음";
    patchEmployee(userId, updates);
  };

  useEffect(() => {
    fetchEmployees(); // async 함수 호출
  }, []);

  return (
    <div
      style={{
        width: "100vw",
        height: "100vh",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      {employees.length > 0 ? (
        employees.map((data: Employee) => {
          return (
            <div
              style={{
                width: "500px",
                height: "50px",
              }}
            >
              <div key={data.id}>
                <text>{data.name} </text>
                <text>{data.part} </text>
                <text>{data.rank} </text>
                <text>{data.role} </text>
                <button
                  onClick={() => {
                    deleteEmployee(data.id);
                  }}
                >
                  삭제
                </button>
                <button
                  onClick={() => {
                    patchEmployees(data.id);
                  }}
                >
                  수정
                </button>
              </div>
            </div>
          );
        })
      ) : (
        <p>데이터가 없습니다.</p>
      )}
    </div>
  );
};
