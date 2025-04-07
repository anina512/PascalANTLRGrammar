program SimpleFunctionTest;
var
  result: Integer;

function GetFive: Integer;
begin
  GetFive := 5;
end;

function GetTen: Integer;
begin
  GetTen := 10;
end;

begin
  result := GetFive;
  writeln('GetFive returned: ', result);
  
  result := GetTen;
  writeln('GetTen returned: ', result);
  
  writeln('Sum is: ', GetFive + GetTen);
end.