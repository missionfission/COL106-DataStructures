function z=PerformXor(x,y)
    [m,n]=size(x);
    z=zeros(m,n);
    for i=1:m
        for j=1:n
            if x(i,j)~=y(i,j)
                z(i,j)=1;
            end
        end
    end