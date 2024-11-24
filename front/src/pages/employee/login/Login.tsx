import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { postLogin } from "../../../api/service";

type Modal = {
  visible: boolean;
  setVisible: (data: boolean) => void;
  message: string;
};

const Modal = (props: Modal) => {
  if (!props.visible) return null;
  return (
    <div
      style={{
        position: "fixed",
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        backgroundColor: "rgba(0, 0, 0, 0.5)",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div
        style={{
          backgroundColor: "white",
          padding: "20px",
          borderRadius: "5px",
          width: "300px",
          textAlign: "center",
        }}
      >
        <text>{props.message}</text>
        <button
          onClick={() => {
            props.setVisible(false);
          }}
        >
          확인
        </button>
      </div>
    </div>
  );
};

const LoginPage = () => {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [visible, setVisible] = useState(false);
  const [message, setMessage] = useState("잘못된 입력입니다.");

  const getData = async () => {
    const response = await postLogin({ name, password });
    return response;
  };

  const validateLogin = async () => {
    try {
      const response = await getData().then((data) => {
        return data;
      });
      console.log(response);
      if (response.statusCode == "OK") {
        navigate("/home");
      } else {
        setMessage(response.resultMsg);
        setVisible(true);
      }
    } catch (e) {
      console.log(e);
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
      <div
        style={{
          padding: 32,
          border: "1px solid",
          borderRadius: 16,
          display: "flex",
          flexDirection: "column",
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
        </div>

        <div
          style={{
            width: "100%",
            height: 48,
            display: "flex",
            justifyContent: "end",
          }}
        >
          <button
            style={{
              flexWrap: "wrap",

              marginTop: 10,
            }}
            onClick={() => {
              navigate("/join");
            }}
          >
            <text style={{ width: 56, height: 32, fontSize: 12 }}>
              회원가입
            </text>
          </button>
          <button
            style={{ flexWrap: "wrap", marginTop: 10 }}
            onClick={() => {
              console.log("제출클릭 " + name);
              validateLogin();
            }}
          >
            <text style={{ width: 56, height: 32, fontSize: 12 }}>제출</text>
          </button>
        </div>
      </div>
      <Modal visible={visible} setVisible={setVisible} message={message} />
    </div>
  );
};

export default LoginPage;
