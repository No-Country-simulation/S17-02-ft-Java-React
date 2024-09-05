import React, { useRef, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useMediaStream from "./useMediaStream";

interface VideoCallModalProps {
  isOpen: boolean;
  onClose: () => void;
}

const VideoCall: React.FC<VideoCallModalProps> = ({ isOpen, onClose }) => {
  const stream = useMediaStream(isOpen);
  const videoRef = useRef<HTMLVideoElement>(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (videoRef.current && stream) {
      videoRef.current.srcObject = stream;
    }
  }, [stream]);

  const handleClose = () => {
    onClose();
    navigate("/");
  };

  if (!isOpen) {
    return null;
  }

  return (
    <div>
      <div>
        <button onClick={handleClose} aria-label="Close">
          X
        </button>
        <div>
          <video ref={videoRef} autoPlay muted playsInline />
        </div>
      </div>
    </div>
  );
};

export default VideoCall;
