import React from "react";

interface FileInputWithPreviewProps {
  onFileChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  imageUrl: string | null;
}

const FileInputWithPreview: React.FC<FileInputWithPreviewProps> = ({ onFileChange, imageUrl }) => {
  return (
    <div>
      <label>Imagen:</label>
      <input type="file" accept="image/*" onChange={onFileChange} />
      {imageUrl && <img src={imageUrl} alt="Vista previa" style={{ width: "100px", height: "100px" }} />}
    </div>
  );
};

export default FileInputWithPreview;
