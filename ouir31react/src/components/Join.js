import React, { useCallback, useState } from "react";
import "./Join.scss";
import "./Input.scss";
import "./Button.scss";
import Button from "./Button.js";
import { useNavigate } from "react-router-dom";
import axios from "axios";


const Join = () => {
  const nav = useNavigate();

  const [chEmail, setChEmail] = useState(false)

  const [form, setForm] = useState({
    uid: "",
    upwd: "",
    uname: "",
    uadd: "",
    uemail: "",
    uphone: ""
  });
  const { uid, upwd, uname, uadd, uemail,uphone } = form;

    //이름, 이메일, 비밀번호, 비밀번호 확인
    const [id, setId] = useState('')
    const [password, setPassword] = useState('')
    const [name, setName] = useState('')
    const [add, setAdd] = useState('')
    const [email, setEmail] = useState('')
    const [phone, setPhone] = useState('')
  
    //오류메시지 상태저장
    const [idMessage, setIdMessage] = useState('')
    const [passwordMessage, setPasswordMessage] = useState('')
    const [nameMessage, setNameMessage] = useState('')
    const [addMessage, setAddMessage] = useState('')
    const [emailMessage, setEmailMessage] = useState('')
    const [phoneMessage, setPhoneMessage] = useState('')
  
    // 유효성 검사
    const [isId, setIsId] = useState(false)
    const [isPassword, setIsPassword] = useState(false)
    const [isName, setIsName] = useState('')
    const [isadd, setIsAdd] = useState('')
    const [isEmail, setIsEmail] = useState(false)
    const [isphone, setIsPhone] = useState('')
  
    // 아이디
    const onChangeId = useCallback((e) => {
      const formObj = {
        ...form,
        [e.target.name]: e.target.value,
      };
      const idRegExp = /^[a-zA-z0-9]{4,12}$/;    

      if (!idRegExp.test(e.target.value)) {
        setIdMessage('4-12사이 대소문자 또는 숫자만 입력해 주세요!');
        setIsId(false);
      } else {
        setIdMessage('사용가능한 아이디 입니다.');
        setIsId(true);
      }
      setForm(formObj);
    }, [form])

     // 비밀번호
    const onChangePassword = useCallback((e) => {
      const formObj = {
        ...form,
        [e.target.name]: e.target.value,
      };      
      const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/
      setPassword(e.target.value)
  
      if (!passwordRegex.test(e.target.value)) {
        setPasswordMessage('숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!')
        setIsPassword(false)
      } else {
        setPasswordMessage('비밀번호 안정성 높음')
        setIsPassword(true)
      }
      setForm(formObj);

    }, [form])


    //이름
    const onChangeName = useCallback((e) => {
      const formObj = {
        ...form,
        [e.target.name]: e.target.value,
      };
   
      const nameRegex = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|]{2,5}$/;
      
      if (!nameRegex.test(e.target.value)) {
        setNameMessage('2글자 이상 5글자 미만으로 입력해주세요.')
        setIsName(false)
      } else {
        setNameMessage('올바른 이름 형식입니다.')
        setIsName(true)
      }
      setForm(formObj);
    }, [form])
  
    // 이메일
    const onChangeEmail = useCallback((e) => {
      const formObj = {
        ...form,
        [e.target.name]: e.target.value,
      };
      const emailRegex =
        /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/
    
  
      if (!emailRegex.test(e.target.value)) {
        setEmailMessage('이메일 형식을 확인해주세요.')
        setIsEmail(false)
      } else {
        setEmailMessage('올바른 이메일 형식입니다.')
        setIsEmail(true)
      }
      setForm(formObj);
    }, [form])

    //핸드폰
    const onChangePhone = useCallback((e) => {
     
      // const phoneRegex = /^[a-zA-z0-9]{4,12}$/;  
      const phonenumber = e.target.value
      .replace(/[^0-9]/g, '')
      .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
      const formObj = {
        ...form,
        [e.target.name]: phonenumber,
      };
      setForm(formObj);
    }, [form])


  const emailcheck = () => {
   
    axios
        .get('/mail/check', {params:{uemail: form.uemail}})
        .then((rm) =>{
            console.log(rm.data);
            if(isEmail===true && rm.data.flag ===true){
              if(rm.data.flag === true){
                var cmsg = prompt("인증번호를 입력해주세요.")
                if(rm.data.msg === cmsg){
                  alert("이메일 인증 완료")
                  setChEmail(true)                  
                }else{
                    alert("인증번호를 확인해주세요.")
                    setChEmail(false)
                }
              }           
            }else if(rm.data.flag === false && isEmail ===true){
              alert("이미 가입된 이메일 입니다.")
              setChEmail(false)
            }
            else{
                alert("이메일을 형식에 맞게 입력해 주세요")
                setChEmail(false)
            }
        })
  };

  const sendJoin = (e) => {
    e.preventDefault();
    console.log(form)
    if(chEmail===false){
      alert('이메일 인증을 진행해주세요.')
      // e.stopPropagation()
      // e.preventdefault()
      return;}      
    axios
      .post('/user/join', form)
      .then((rm) => {
        if (rm.data.flag === true) {
          alert("가입 성공");
          nav("/home");
        }
      })
      .catch((error) => console.log(error));
  };

  // const onChange = useCallback(
  //   (e) => {
  //     const formObj = {
  //       ...form,
  //       [e.target.name]: e.target.value,
  //     };
  //     setForm(formObj);
  //   },[form]);

    
  return (
    <>
    <div className="Join">
      <form className="Content" onSubmit={sendJoin}>
        <h1>회원 가입</h1>
       
        <input
          className="Input"
          name="uid"
          value={uid}
          placeholder="아이디"
          onChange={onChangeId}
          autoFocus
          required
        />        
        {uid.length > 0 && <span className={`message ${isId ? 'success' : 'error'}`}>{idMessage }</span>}        
        
        <input
          type="password"
          className="Input"
          name="upwd"
          value={upwd}
          placeholder="비밀번호"
          onChange={onChangePassword}
          required
        />
        {upwd.length > 0 && (<span className={`message ${isPassword ? 'success' : 'error'}`}>{passwordMessage}</span>)}
       
        <input
          className="Input"
          name="uname"
          value={uname}
          placeholder="이름"
          onChange={onChangeName}
          required
        />
        {uname.length > 0 && <span className={`message ${isName ? 'success' : 'error'}`}>{nameMessage}</span>}

          {/* <input
          className="Input"
          name="uadd"
          value={uadd}
          placeholder="주소"
          onChange={onChange}
          required
        /> */}
          <input
          className="Input"
          name="uemail"
          value={uemail}
          placeholder="이메일"
          onChange={onChangeEmail}
          required
        />
         {uemail.length > 0 && <span className={`message ${isEmail ? 'success' : 'error'}`}>{emailMessage}</span>}

         <Button color="ouir1" onClick={emailcheck}>
          이메일 인증
        </Button>
        <input
          maxlength="13"
          className="Input"
          name="uphone"
          value={uphone}
          placeholder="연락처"
          onChange={onChangePhone}
          required
        />      
        <Button type="submit">
          가입
        </Button>    
      </form>
    </div>
    </>
  );
};

export default Join;