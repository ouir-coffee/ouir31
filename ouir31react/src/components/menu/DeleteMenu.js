import React from "react";
import Modal from "../Modal/Modal";

const DeleteMenu = (props) => {
  const { open, close, submit } = props;
  return (
    <Modal open={open} close={close} submit={submit} header="Delete">
      <input />
      <input />
      <input />
      <input />
      <input />
    </Modal>
  );
};

export default DeleteMenu;
