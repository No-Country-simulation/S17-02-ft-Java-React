import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";

interface VideoCallModalProps {
  isOpen: boolean;
  onClose: () => void;
}

const VideoCall: React.FC<VideoCallModalProps> = ({ isOpen }) => {
  const [stream, setStream] = useState<MediaStream | null>(null);
  const videoRef = useRef<HTMLVideoElement>(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (isOpen) {
      navigator.mediaDevices
        .getUserMedia({ video: true, audio: true })
        .then((mediaStream) => {
          setStream(mediaStream);
        })
        .catch((err) => {
          console.error("Error accessing media devices.", err);
        });

      return () => {
        if (stream) {
          stream.getTracks().forEach((track) => track.stop());
        }
      };
    }
  }, [isOpen]);

  useEffect(() => {
    if (videoRef.current && stream) {
      videoRef.current.srcObject = stream;
    }
  }, [stream]);

  if (!isOpen) {
    return null;
  }

  const handleClose = () => {
    navigate("/");
  };

  return (
    <div>
      <div>
        <button onClick={handleClose}>X</button>
        <div>
          <video ref={videoRef} autoPlay muted playsInline />
        </div>
      </div>
    </div>
  );
};

export default VideoCall;
