import { useState } from "react";
import { postJoin } from "../../../api/service";
import { useNavigate } from "react-router-dom";
import { Role } from "../../../api/type";

export const Join = () => {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [status, setStatus] = useState("");
  const [currentValue, setCurrentValue] = useState<Role>("USER");
  const navigate = useNavigate();

  const [isDropdownView, setDropdownView] = useState(false);

  const handleClickContainer = () => {
    setDropdownView(!isDropdownView);
  };

  const handleBlurContainer = () => {
    setTimeout(() => {
      setDropdownView(false);
    }, 200);
  };

  const join = async () => {
    const result = await postJoin({
      name: name,
      password: password,
      role: currentValue,
    });

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

        <div
          className="container"
          style={{ position: "relative" }}
          onBlur={handleBlurContainer}
        >
          <label onClick={handleClickContainer}>
            <button>
              {currentValue}
              {isDropdownView ? "▲" : "▼"}
            </button>
          </label>
          {isDropdownView && (
            <div
              style={{
                position: "absolute",
                top: "100%",
                left: "0" /* 왼쪽 정렬 */,
                backgroundColor: "white",
                border: "1px solid #ccc",
                padding: "8px",
                zIndex: "10",
                width: "150px",
                boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
              }}
            >
              <ul>
                {["ADMIN", "USER"].map((li, i) => (
                  <li onClick={() => setCurrentValue(li)}>{li}</li>
                ))}
              </ul>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};
