import { useState } from "react";
import { postJoin } from "../../../api/service";
import { useNavigate } from "react-router-dom";

export const Join = () => {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [status, setStatus] = useState("");
  const navigate = useNavigate();

  const join = async () => {
    const result = await postJoin({ name: name, password: password });

    if (result.statusCode == "BAD_REQUEST") {
      setStatus(result.resultMsg);
    } else {
      navigate("../");
    }
  };

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
      <div style={{ flexDirection: "row" }}>
        <text> 이름 : </text>
        <input
          value={name}
          onChange={(data) => {
            setName(data.target.value);
          }}
          placeholder="이름을 입력해주세요"
        ></input>

        <text> 비밀번호 : </text>
        <input
          value={password}
          onChange={(data) => {
            setPassword(data.target.value);
          }}
          placeholder="비밀번호를 입력해주세요"
        ></input>
        <button onClick={join}>가입</button>
        {status != "" && <text>{status}</text>}
      </div>
    </div>
  );
};
