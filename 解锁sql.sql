

select count(*) from v$locked_object; --��ѯ��������

select b.owner,b.object_name,a.session_id,a.locked_mode 
from v$locked_object a, dba_objects b where b.object_id = a.object_id; --��ѯ��Щ����

select b.username,b.sid,b.serial#,logon_time from v$locked_object a,v$session b  
where a.session_id = b.sid order by b.logon_time; --��ѯ�������sid��serial

alter system kill session '289,1323'; --����ɱ���� 'sid,serial'

declare cursor mycur is
select b.sid,b.serial#
       from v$locked_object a,v$session b 
       where a.SESSION_ID = b.SID group by
      b.SID,b.SERIAL#;
      
begin
    for cur in mycur
       loop
         execute immediate('alter system kill session'''||cur.sid ||','||cur.serial#||'''');
       end loop;
end; --��������
Error: '5455ea6fff53ef11a5a94fe3d6039cbd', actual: '0bbd230b263efd24eb1bfc44b330ef54'  
