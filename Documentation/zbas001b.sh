#!/bin/ksh
#*****************************************************************************
#*  Program Name: zbas001b.sh
#*****************************************************************************
#*  Author......: Johnny Bridges
#*  Date........: 3/02/2001
#*  Owner.......: PeopleSoft Technical Team
#*  Project Id..:
#*****************************************************************************
#*  Program Summary: This program will will ftp from Unix to a remote server.
#*                   It accept two parms:
#*                   The first parm is where the file will be coming from and
#*                   the second parm is where the file is going.
#*****************************************************************************
#*
#*  Input/Output Files:
#*
#*  FILE NAME         INPUT/OUTPUT        DESCRIPTION
#*  ---------         ------------        -----------
#*
#*****************************************************************************
#*  Modifications:
#*****************************************************************************
#*  Author............: Johnny Bridges
#*  Date..............: 2001-03-26
#*  Owner.............: PSTechTeam
#*  Project Id........: N/A
#*  Purpose of change.: Added error handling as well as changed to retrieve
#*                      the target or remote machine from the PSOFT environment
#*                      variable $RMTSVR (remote server)
#* 05/06/2002           SMeyer/Z_UPGRADE83_E98405 - Passing RMTSVR as a parameter.
#*                      PSOFT environment variable $RMTSVR (remote server)is not defined
#*                      in v8.3 environment.
#*****************************************************************************

RMTSVR=$3

if [[ -z $RMTSVR ]];then   #If RMTSVR is null
    echo 'The remote server variable (RMTSVR) is null.'
    exit 1    #Return not success.
elif [[ $RMTSVR = ' ' ]];then  #If RMTSVR is blank
    echo 'The remote server variable (RMTSVR) is blank.'
    exit 1    #Return not success.
else
    grep -q "machine *$RMTSVR " $HOME/.netrc  #Check to see if the machine is in the .netrc file.
    if [[ $? -ne 0 ]];then   #If command returned error code.
        echo 'The machine does not exist in the .netrc file.'
        exit 1
    else
# THIS FTP PORTION OF THE CODE MUST BE LEFT JUSTIFIED FOR THIS CODE TO FUNCTION.
ftp -v <<!
open $RMTSVR
quote site namefmt 0
ascii
put "$1" "$2"
bye
!
        if [[ $? -ne 0 ]];then   #If command returned error code.
            echo 'The FTP command failed.'
            exit 1     #Return not success.
        fi
    fi
fi
