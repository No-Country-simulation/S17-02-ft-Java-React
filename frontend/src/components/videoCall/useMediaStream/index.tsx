import { useState, useEffect } from "react";

const useMediaStream = (isOpen: boolean) => {
  const [stream, setStream] = useState<MediaStream | null>(null);

  useEffect(() => {
    let localStream: MediaStream | null = null;

    if (isOpen) {
      navigator.mediaDevices
        .getUserMedia({ video: true, audio: true })
        .then((mediaStream) => {
          localStream = mediaStream;
          setStream(mediaStream);
        })
        .catch((err) => {
          console.error("Error accessing media devices.", err);
        });

      return () => {
        if (localStream) {
          localStream.getTracks().forEach((track) => track.stop());
        }
      };
    }
  }, [isOpen]);

  return stream;
};

export default useMediaStream;
