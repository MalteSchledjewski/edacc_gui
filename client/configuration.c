#include "configuration.h"
#include "log.h"
#include "global.h"
#include "safeio.h"

//Sets *lineptr to newly allocated memory containing the next line in stream.
//*lineptr is '\0' terminated and includes the newline character, if one was found.
//On error or on EOF condition, the function returns -1. Otherwise, the return value is 0.
int readLine(char **lineptr, FILE *stream) {
    int c;
    long start, end;
    char *nextPos;

    //Set start resp. end to the current offset in stream resp. the offset
    //of the next '\n' character or EOF in stream
    start=ftell(stream);
    if(start==-1)
        return -1;
    do {
        if((c=safeGetc(stream))==EOF)
            break;
    } while(c!='\n');
    end=ftell(stream);
    if(end==-1 || end==start) {
        return -1;
    }

    //Allocate memory of the correct size
    *lineptr=(char*)calloc(end-start+1, 1);
    if(*lineptr==NULL)
        return -1;
    nextPos=*lineptr;

    //Copy the line from stream to *lineptr
    if(fseek(stream, start, SEEK_SET)==-1) {
        free(*lineptr);
        return -1;
    }
    for(;;) {
        if((c=safeGetc(stream))==EOF)
            break;
        *nextPos=(char)c;
        if(c=='\n')
            break;
        ++nextPos;
    }

    return 0;
}

status read_config() {
    char *lineptr;
    FILE *conf;
    char *key=NULL;
    char *value=NULL;
    char *end=NULL;
    int valueLen, keyLen;

    if((conf = fopen("./config", "r")) == NULL) {
        logError("could not open configuration file!\n");
        return sysError;
    }

    while(readLine(&lineptr, conf) != -1) {
        //Terminate both the key and value substrings with '\0' within lineptr
        //and set the key resp. value pointer to the beginning of the corresponding substring
        key=lineptr;
        for(value=lineptr; *value!=' ' && *value!='='; ++value) {
            if(*value=='\0') {
                goto DONE;
            }
        }
        *value='\0';
        for(++value; *value=='=' || *value==' '; ++value) {
            if(*value=='\0') {
                goto DONE;
            }
        }
        for(end=value; *end!='\0' && *end!='\n'; ++end);
        *end='\0';
        valueLen=strlen(value);
        keyLen=strlen(key);
        if(valueLen==0 || keyLen==0) {
            goto DONE;
        }

        if(strcmp(key, "host") == 0) {
            host=malloc(valueLen+1);
            if(host==NULL) {
                logError("Out of memory\n");
                fclose(conf);
                free(lineptr);
                return sysError;
            }
            strcpy(host, value);
        } else if(strcmp(key, "username") == 0) {
            username=malloc(valueLen+1);
            if(username==NULL) {
                logError("Out of memory\n");
                fclose(conf);
                free(lineptr);
                return sysError;
            }
            strcpy(username, value);
        } else if(strcmp(key, "password") == 0) {
            password=malloc(valueLen+1);
            if(password==NULL) {
                logError("Out of memory\n");
                fclose(conf);
                free(lineptr);
                return sysError;
            }
            strcpy(password, value);
        } else if(strcmp(key, "database") == 0) {
            database=malloc(valueLen+1);
            if(database==NULL) {
                logError("Out of memory\n");
                fclose(conf);
                free(lineptr);
                return sysError;
            }
            strcpy(database, value);
        } else if(strcmp(key, "experiment") == 0) {
            experimentId = atoi(value);
        } else if(strcmp(key, "gridqueue") == 0) {
            gridQueueId = atoi(value);
        } else {
            printf("unknownn option %s\n", key);
            fclose(conf);
            free(lineptr);
            return sysError;
        }

        DONE:
        free(lineptr);
    }
    logComment(1,"information from config-file:\nhost:  %s  \n",host);
    logComment(1,"username: %s \n",username);
    logComment(1,"password: **********\n");
    logComment(1,"DB: %s\n",database);
    logComment(1,"experiment: %d\n",experimentId);
    fclose(conf);
    return success;
}

