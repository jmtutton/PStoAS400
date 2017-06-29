#!/usr/bin/ksh
#*****************************************************************************
#*  Program Name:  zbas002b.sh
#*****************************************************************************
#*  Author......:  Johnny Bridges
#*  Date........:  2001-04-02
#*  Owner.......:  PSTECHTEAM
#*  Project Id..:  PeopleSoft Rehost to Unix
#*****************************************************************************
#*  Program Summary: This program will be used to execute an REXEC command
#*                   from an external source such as an SQR.  It requires a
#*                   single parameter be passed consisting of the command to
#*                   be executed on the remote machine.
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
#*
#* 05/02/2002           SMeyer/Z_UPGRADE83_E98405 - Passing RMTSVR as a parameter.
#*                      PSOFT environment variable $RMTSVR (remote server)is not defined
#*                      in v8.3 environment.
#*****************************************************************************

RMTSVR=$2

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
        rexec $RMTSVR "$1" #Execute the command on the remote server.
        if [[ $? -ne 0 ]];then   #If command returned error code.
            echo 'The REXEC command failed.'
            exit 1     #Return not success.
        fi
    fi
fi
