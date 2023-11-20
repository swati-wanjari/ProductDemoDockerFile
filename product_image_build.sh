cd DbDocker
docker build --tag=mypostgre:latest .

# shellcheck disable=SC2103
cd ..
docker build --tag=productservice:latest .

