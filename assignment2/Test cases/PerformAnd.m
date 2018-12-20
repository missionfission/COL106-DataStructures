function z=PerformAnd(x,y)
    [m,n]=size(x);
    z=zeros(m,n);
    for i=1:m
        for j=1:n
            if x(i,j)==1 && y(i,j)==1
                z(i,j)=1;
            end
        end
    end