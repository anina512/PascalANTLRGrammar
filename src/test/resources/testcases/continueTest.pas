program ContinueTest;
var
  i: Integer;
begin
  for i := 1 to 10 do
  begin
    if i mod 2 = 0 then
      continue;  { Skip even numbers }
    
    writeln(i);  { Only print odd numbers }
  end;
  
  writeln('Loop completed with continue');
end.