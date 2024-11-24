import { useState } from "react";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const navigate = useNavigate();
  const [name, setName] = useState("");

  return (
    <div
      style={{
        width: "100vw",
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div
        style={{
          padding: 32,
          border: "1px solid",
          borderRadius: 16,
          display: "flex",
          flexDirection: "column",
        }}
      >
        <div>
          <text> 이름 : </text>
          <input
            value={name}
            onChange={(data) => {
              console.log(data);
              setName(data.target.value);
            }}
            placeholder="이름을 입력해주세요"
          ></input>
        </div>

        <div
          style={{
            width: "100%",
            height: 48,
            display: "flex",
            justifyContent: "end",
          }}
        >
          <button>회원가입</button>
          <button
            style={{ width: 56, height: 32, marginTop: 10, fontSize: 12 }}
            onClick={() => {
              console.log("제출클릭 " + name);
              navigate("/home");
            }}
          >
            제출
          </button>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
